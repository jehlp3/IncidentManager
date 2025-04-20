1º Criação da tabela <br>
2º Entity <br>
3º Enums <br>
4º Repository - Toda classe precisa ter<br>
5º Service - Anexos e Interações dizem respeito ao Chamado, nem toda classe terá um service<br>
6º Domain <br>
7º Dto <br>
8º Mapper <br>
9º Controller <br>
10º <br>


#TODO
Ao criar o banco de dados, altere a senha para VARCHAR(100) e deixe o 
username UNIQUE.

#Provável caminho Documentação - Não funcional
http://localhost:8081/api/v1/swagger-ui/index.html
Aula 4


url: http://localhost:8081/api/v1/usuarios
{
"username": "admin7",
"senha": "admin123",
"nome": "administrador7",
"email": "admin7@email.com",
"telefone": "11911112267",
"ativo": true,
"ehTecnicoTi": true,
"ehTecnicoNivelDois": true,
"ehAdministrador": true

}


http://localhost:8081/api/v1/chamados
{
"assunto": "Não envio de e-mail",
"descricao": "Nesta manhã não recebemos e-mails de envio",
"tipo_solicitacao": "REQUISICAO_SUPORTE_TI",
"criticidade": "ALTA",
"impacto": "SISTEMA_ESPECIFICO",
"criadoPorUsuarioId": "crie um usuário primeiro e acrescente aqui o id",
"ativo": true,
"foi_escalado": false
}

#TODO Status
Ao criar = ABERTO
EM_PROGRESSO ou AGUARDANDO_SOLICITANTE
Criar um endpoint apenas para resolver o chamado = o Status deverá ser ou EM_PROGRESSO ou AGUARDANDO_SOLICITANTE,
Criar um endpoint apenas para cancelar o chamado 