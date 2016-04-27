package br.com.battycode.dto;

public enum Esfera {

    Municipal (1),
    Estadual  (2),
    Federal   (3);

    private final int codigo;

    Esfera(int codigo) {
        this.codigo = codigo;
    }

    public static Esfera getEsfera(int codigoEsfera){
        if (codigoEsfera == 1){
            return Esfera.Municipal;
        }
        if (codigoEsfera == 2){
            return Esfera.Estadual;
        }
        return Esfera.Federal;
    }
}
