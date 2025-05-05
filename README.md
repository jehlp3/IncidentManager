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



#Provável caminho Documentação - Não funcional
http://localhost:8081/api/v1/swagger-ui/index.html
Aula 4


url: http://localhost:8081/api/v1/usuarios
POST (Sem autenticação)
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
POST (Com autenticação)
{
"assunto": "Não envio de e-mail",
"descricao": "Nesta manhã não recebemos e-mails de envio",
"tipo_solicitacao": "REQUISICAO_SUPORTE_TI",
"criticidade": "ALTA",
"impacto": "SISTEMA_ESPECIFICO",
"ativo": true,
"foi_escalado": false
}

http://localhost:8081/api/v1/chamados/f9dcae4e-cf97-449a-9e8a-1a5955b05d57/interacao
POST (Com autenticação)
{
"mensagem": "Já iniciamos o seu atendimento, poderia informar de quais usuários os e-mails não foram disparados?",
"anexos": [
{
"filename": "teste.png",
"content": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAABBsAAAEgCAYAAAD1x3sNAAAAAXNSR0IArs4c6QAAAARnQU1BAAC..."
}
]
}

http://localhost:8081/api/v1/chamados
GET (Sem autenticação)

http://localhost:8081/api/v1/chamados/236a376c-9eee-48c2-bed8-440e5cb71a89/escala
POST

http://localhost:8081/api/v1/chamados/236a376c-9eee-48c2-bed8-440e5cb71a89/resolve
POST

http://localhost:8081/api/v1/chamados/c9f064e0-5d0b-4ac1-aa8f-40260d4e7319/cancela
POST

Busca usuário pelo id
http://localhost:8081/api/v1/usuarios/f542f494-caf2-4386-bd6b-75b79b53e7df
GET

#TODO Status
Ao criar = ABERTO = ok
Criar um endpoint apenas para resolver o chamado = o Status deverá ser ou EM_PROGRESSO ou AGUARDANDO_SOLICITANTE = ok
Criar um endpoint apenas para cancelar o chamado = ok
Alterar o endpoint cancelar o chamado para exigir um comentário
Documentação

Programa que converte os anexos em Base64
https://www.base64-image.de/

#Dica
Caso não funcione de primeira, vá em Maven>lifecicle>clean

Provável caminho das variáveis, necessário validar
1º URL
2º Domain
3º Service
4º Entity 
5º Repository

