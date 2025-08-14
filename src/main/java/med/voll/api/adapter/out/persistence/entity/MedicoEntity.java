package med.voll.api.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.model.AtualizarMedicoDTO;
import med.voll.api.domain.model.DadosMedicoDTO;
import med.voll.api.domain.model.Especialidade;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class MedicoEntity {

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nome;
        private String telefone;
        private String email;
        private String crm;

        @Enumerated(EnumType.STRING)
        private Especialidade especialidade;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "endereco_id")
        private EnderecoEntity endereco;

        private Boolean ativo;

        public MedicoEntity(DadosMedicoDTO medico) {
                this.ativo = true;
                this.nome = medico.nome();
                this.crm = medico.crm();
                this.email = medico.email();
                this.telefone = medico.telefone();
                this.especialidade = medico.especialidade();
                this.endereco = new EnderecoEntity(medico.endereco());
        }

        public void atualizarInformacoes(AtualizarMedicoDTO dadosMedico){

                if(dadosMedico.nome() != null) this.nome = dadosMedico.nome();
                if(dadosMedico.telefone() != null) this.telefone = dadosMedico.telefone();
                if(dadosMedico.email() != null) this.email = dadosMedico.email();
                if(dadosMedico.endereco() != null) this.endereco = new EnderecoEntity(dadosMedico.endereco());





        }

        public void excluir() {
                this.ativo = false;
        }
}
