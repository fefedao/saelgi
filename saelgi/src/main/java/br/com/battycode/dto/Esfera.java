package br.com.battycode.dto;

public enum Esfera {
	
	Municipal (1),
    Estadual  (2),
    Federal   (3);

    private final int codigo;

    Esfera(int codigo) {
        this.codigo = codigo;
    }

}
