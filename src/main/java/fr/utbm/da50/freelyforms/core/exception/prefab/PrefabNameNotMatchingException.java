package fr.utbm.da50.freelyforms.core.exception.prefab;

import lombok.NonNull;

public class PrefabNameNotMatchingException extends Exception{

    public PrefabNameNotMatchingException(@NonNull String message){
        super(message);
    }
}
