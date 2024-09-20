$(document).ready(function () {
    function listarTarefas() {
        $.ajax({
            url: "/tarefa/listar",
            method: "GET",
            dataType: "json",
            success: function (tarefas) {
                $("#tabelaTarefas tbody").empty();

                $.each(tarefas, function (index, tarefa) {
                    var linha = `
                        <tr>
                            <td>${tarefa.id}</td>
                            <td>${tarefa.tipo}</td>
                            <td>${tarefa.descricao}</td>
                            <td>${tarefa.instrucoes}</td>
                            <td>${tarefa.data}</td>
                            <td>${tarefa.responsavel.nome}</td>
                            <td>${tarefa.status ? 'Concluído' : 'Pendente'}</td>
                            <td><button class="btn btn-warning atualizar" data-id="${tarefa.id}">Atualizar</button></td>
                            <td><button class="btn btn-danger deletar" data-id="${tarefa.id}">Excluir</button></td>
                        </tr>`;
                    $("#tabelaTarefas tbody").append(linha);
                });
            },
            error: function () {
                alert("Erro ao carregar tarefas. Tente novamente mais tarde.");
            }
        });
    }

    // Evento atualizar
    $(document).on("click", ".atualizar", function () {
        var id = $(this).data("id");
        window.location.href = "/atualizarTarefaForm/" + id; // Redireciona para a página de atualização
    });

    // Evento excluir
    $(document).on("click", ".deletar", function () {
        var id = $(this).data("id");
        if (confirm("Você tem certeza que deseja excluir esta tarefa?")) {
            $.ajax({
                url: "/tarefa/deletar/" + id,
                method: "DELETE",
                success: function () {
                    alert('Tarefa excluída com sucesso!');
                    listarTarefas(); // Atualiza a lista de tarefas após exclusão
                },
                error: function () {
                    alert('Erro ao tentar excluir a tarefa.');
                }
            });
        }
    });

    // Chama a função para listar tarefas quando o documento estiver pronto
    listarTarefas();
});
