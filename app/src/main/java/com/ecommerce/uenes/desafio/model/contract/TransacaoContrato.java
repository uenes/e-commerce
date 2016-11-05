package com.ecommerce.uenes.desafio.model.contract;

import android.provider.BaseColumns;

public final class TransacaoContrato {

    private TransacaoContrato () {}

    public static class Transacao implements BaseColumns {
        public static final String TABLE_NAME = "transacao";
        public static final String COLUMN_NOME_CLIENTE = "nomeCliente";
        public static final String COLUMN_NUMERO_CARTAO = "numeroCartao";
        public static final String COLUMN_VENCIMENTO = "vencimento";
        public static final String COLUMN_BANDEIRA = "bandeira";
        public static final String COLUMN_CVV = "cvv";
        public static final String COLUMN_VALOR = "valor";

        private String nomeCliente;
        private Long numeroCartao;
        private String vencimentoCartao;
        private String bandeiraCartao;
        private Integer Cvv;
        private Float valor;

        public String getNomeCliente() {
            return nomeCliente;
        }

        public void setNomeCliente(String nomeCliente) {
            this.nomeCliente = nomeCliente;
        }

        public Long getNumeroCartao() {
            return numeroCartao;
        }

        public void setNumeroCartao(Long numeroCartao) {
            this.numeroCartao = numeroCartao;
        }

        public String getVencimentoCartao() {
            return vencimentoCartao;
        }

        public void setVencimentoCartao(String vencimentoCartao) {
            this.vencimentoCartao = vencimentoCartao;
        }

        public Integer getCvv() {
            return Cvv;
        }

        public void setCvv(Integer cvv) {
            Cvv = cvv;
        }

        public Float getValor() {
            return valor;
        }

        public void setValor(Float valor) {
            this.valor = valor;
        }

        public String getBandeiraCartao() {
            return bandeiraCartao;
        }

        public void setBandeiraCartao(String bandeiraCartao) {
            this.bandeiraCartao = bandeiraCartao;
        }
    }
}
