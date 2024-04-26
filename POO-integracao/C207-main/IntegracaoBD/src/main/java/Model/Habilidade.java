package Model;

public class Habilidade {
        String nome;
        String tipo;
        String efeito;
        int dano;
        int velocidade;
        int cooldown;

        public Habilidade(String nome, String tipo, int dano, int velocidade, int cooldown, String efeito){
            this.nome = nome;
            this.tipo = tipo;
            this.dano = dano;
            this.velocidade = velocidade;
            this.cooldown = cooldown;
            this.efeito = efeito;

        }

        public String getNome() {
            return nome;
        }

        public String getTipo() {
            return tipo;
        }

        public Integer getDano() {
            return dano;
        }

        public Integer getVelocidade() {
            return velocidade;
        }

        public Integer getCooldown() {
            return cooldown;
        }

        public String getEfeito() {
        return efeito;
    }

}

