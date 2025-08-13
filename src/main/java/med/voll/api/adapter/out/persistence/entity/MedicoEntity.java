package med.voll.api.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.model.DadosMedico;
import med.voll.api.domain.model.Especialidade;
import med.voll.api.domain.model.response.MedicoResponse;

import java.util.ArrayList;
import java.util.List;

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

        public MedicoEntity(DadosMedico medico) {
                this.nome = medico.nome();
                this.crm = medico.crm();
                this.email = medico.email();
                this.telefone = medico.telefone();
                this.especialidade = medico.especialidade();
                this.endereco = new EnderecoEntity(medico.endereco());
        }
}
