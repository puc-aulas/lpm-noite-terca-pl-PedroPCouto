package br.com.pucminas.controledebiblioteca.adapters;

import br.com.pucminas.controledebiblioteca.enums.Generos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CursoAdapter {
    public static List<Generos> adptCursoToInteresses(String nomeCurso){
        if(nomeCurso.equals("")){
            return new ArrayList<>();
        }
        Map<String,List<Generos>> generosPorCurso = new HashMap<>();
        generosPorCurso.put("Ciências da Computação", new ArrayList<>());
        generosPorCurso.put("Medicina", new ArrayList<>());
        generosPorCurso.put("Engenharia de Software", new ArrayList<>());
        generosPorCurso.put("Medicina Veterinária", new ArrayList<>());
        if(!generosPorCurso.containsKey(nomeCurso)){
            return new ArrayList<>();
        }

        generosPorCurso.get("Ciências da Computação").add(Generos.HARDWARE);
        generosPorCurso.get("Ciências da Computação").add(Generos.INFORMATICA);
        generosPorCurso.get("Medicina").add(Generos.MEDICINA);
        generosPorCurso.get("Engenharia de Software").add(Generos.HARDWARE);
        generosPorCurso.get("Engenharia de Software").add(Generos.INFORMATICA);
        generosPorCurso.get("Medicina Veterinária").add(Generos.VETERINARIA);

        return generosPorCurso.get(nomeCurso);
    }
}
