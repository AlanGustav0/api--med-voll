package med.voll.api.entities;

import med.voll.api.Especialidade;

public record DadosMedico(String nome, String email, String crm, Especialidade especialidade, Endereco endereco){}
