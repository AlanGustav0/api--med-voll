package med.voll.api.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.model.DadosPacienteDTO;

@Table(name = "paciente")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class PacienteEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    String email;
    String telefone;
    String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    EnderecoEntity endereco;

    Boolean ativo;

    public PacienteEntity(DadosPacienteDTO paciente) {
        this.ativo = true;
        this.nome = paciente.nome();
        this.email = paciente.email();
        this.telefone = paciente.telefone();
        this.cpf = paciente.cpf();
        this.endereco = new EnderecoEntity(paciente.endereco());
    }

    public void atualizarInformacoes(DadosPacienteDTO dadosPaciente) {
        if(dadosPaciente.nome() != null){
            this.nome = dadosPaciente.nome();
        }

        if(dadosPaciente.email() != null){
            this.email = dadosPaciente.email();
        }

        if(dadosPaciente.telefone() != null){
            this.telefone = dadosPaciente.telefone();
        }

        if(dadosPaciente.endereco() != null){
            this.endereco = new EnderecoEntity(dadosPaciente.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
