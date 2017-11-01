package experts;

import dto.DTORut;
import entities.Rut;

public class ExpertoValidarRut {
	
	// Método para genrar Rut
	public DTORut generarRut() {
		Rut rut = new Rut();
		return new DTORut();
	}

	public boolean validarRut(String vrut)
    {
        boolean flag = false;
        String rut = vrut.substring(0, vrut.length() - 1);

        String posibleVerificador = vrut.charAt(vrut.length() - 1)+"";
        int cantidad = rut.length();
        int factor = 2;
        int suma = 0;
        String verificador = "";

        for(int i = cantidad; i > 0; i--)
        {
            if(factor > 7)
            {
                factor = 2;
            }
            suma += (Integer.parseInt(rut.substring((i-1), i)))*factor;
            factor++;

        }
        verificador = String.valueOf(11 - suma%11);
        if(verificador.equals(posibleVerificador))
        {
            flag = true;
        }
        else
        {
            if((verificador.equals("10")) && (posibleVerificador.toLowerCase().equals("k")))
            {
                flag = true;
            }
            else
            {
                if((verificador.equals("11") && posibleVerificador.equals("0")))
                {
                    flag = true;
                }
                else
                {
                    flag = false;
                }
            }
        }
        return flag;        
    }  
}
