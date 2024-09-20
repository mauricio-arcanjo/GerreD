$(document).ready(function () {

    function listarUsuarios() {
        $.ajax({
            url: "/usuario/listar",
            method: "GET",
            datatype: "json",
            success: function (usuarios) {

                // Limpar o corpo da tabela antes de adicionar os dados
                $("#tabelaUsuarios tbody").empty();

                // Correção no uso do $.each
                $.each(usuarios, function (index, usuario) {
                    var linha = ` 
                        <tr>
                            <td>${usuario.id}</td>
                            <td>${usuario.nome}</td>
                            <td>${usuario.email}</td>
                            <td>${usuario.senha}</td>
                            <td>${usuario.tipoUsuario}</td>
                            <td><button class="atualizar" data-id="${usuario.id}">Atualizar</button></td>
                            <td><button class="deletar" data-id="${usuario.id}">Excluir</button></td>
                        </tr>`;

                    // Adiciona a linha ao corpo da tabela
                    $("#tabelaUsuarios tbody").append(linha);
                });
            },
            error: function () {
                alert("Erro ao carregar usuários. Tente novamente mais tarde.");
            }
        });
    }

    // Evento atualizar
    $(document).on("click", ".atualizar", function () {
        var id = $(this).data("id"); // Corrigido o acesso ao 'data-id'
        $.ajax({
            url: "/usuario/pesquisar/" + id,
            method: "GET",
            dataType: "json",
            success: function (usuario) {

                window.location.href = "/atualizarUsuarioForm/" + usuario.id;

            },
            error: function () {
                alert('Erro ao tentar atualizar o usuário.');
            }
        });
    });

    // Evento apagar
    $(document).on("click", ".deletar", function () {
        var id = $(this).data("id"); // Corrigido o acesso ao 'data-id'
        $.ajax({
            url: "/usuario/deletar/" + id,
            method: "DELETE",
            success: function () {
                alert('Usuário apagado com sucesso!');
                // Atualiza a lista de usuários após a exclusão
                listarUsuarios();
            },
            error: function () {
                alert('Erro ao tentar excluir o usuário.');
            }
        });
    });

    // Chama a função para listar usuários quando o documento estiver pronto
    listarUsuarios();
});
