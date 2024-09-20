$(document).ready(function () {
    function listarItensCompra() {
        $.ajax({
            url: "/item/listar", // Alterar para a rota correta dos itens de compra
            method: "GET",
            dataType: "json",
            success: function (itens) {
                $("#tabelaItensCompra tbody").empty(); // Limpar o corpo da tabela antes de adicionar as novas linhas

                $.each(itens, function (index, item) {
                    var linha = `
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.nomeItem}</td>
                            <td>${item.tipo}</td>
                            <td>${item.valor}</td>
                            <td>${item.responsavel ? item.responsavel.nome : 'Não atribuído'}</td>
                            <td>${item.status ? 'Concluído' : 'Pendente'}</td>
                            <td><button class="btn btn-warning atualizar" data-id="${item.id}">Atualizar</button></td>
                            <td><button class="btn btn-danger deletar" data-id="${item.id}">Excluir</button></td>
                        </tr>`;
                    $("#tabelaItensCompra tbody").append(linha);
                });
            },
            error: function () {
                alert("Erro ao carregar os itens de compra. Tente novamente mais tarde.");
            }
        });
    }

    // Evento atualizar
    $(document).on("click", ".atualizar", function () {
        var id = $(this).data("id");
        window.location.href = "/atualizarItemCompraForm/" + id; // Redireciona para a página de atualização
    });

    // Evento excluir
    $(document).on("click", ".deletar", function () {
        var id = $(this).data("id");
        if (confirm("Você tem certeza que deseja excluir este item de compra?")) {
            $.ajax({
                url: "/item/deletar/" + id,
                method: "DELETE",
                success: function () {
                    alert('Item de compra excluído com sucesso!');
                    listarItensCompra(); // Atualiza a lista de itens de compra após exclusão
                },
                error: function () {
                    alert('Erro ao tentar excluir o item de compra.');
                }
            });
        }
    });

    // Chama a função para listar itens de compra quando o documento estiver pronto
    listarItensCompra();
});
