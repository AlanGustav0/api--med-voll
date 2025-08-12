package med.voll.api.domain.model;

import med.voll.api.Especialidade;

public record DadosMedico(String nome, String email, String crm, Especialidade especialidade, Endereco endereco){}
