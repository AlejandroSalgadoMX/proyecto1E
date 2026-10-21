package mx.edu.utez.proyecto1E.service;

import mx.edu.utez.proyecto1E.controller.dtos.RequestCalculadoraDTO;
import mx.edu.utez.proyecto1E.controller.dtos.ResponseCalculadoraDTO;
import mx.edu.utez.proyecto1E.exception.customExceptions.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public ResponseCalculadoraDTO calculadora(RequestCalculadoraDTO payload){
        ResponseCalculadoraDTO respuesta = new ResponseCalculadoraDTO();

        if (!payload.getOperacion().equals("SUMA") && !payload.getOperacion().equals("RESTA")
                && !payload.getOperacion().equals("MULTIPLICACION")
        ){
            throw new CustomBadRequestException("La operacion solicitada no es valida, Solo SUMA, RESTA o MULTIPLICACION");
        }

        switch (payload.getOperacion()){
            case "SUMA":
                respuesta.setRespuesta(
                        payload.getNum1() + payload.getNum2()
                );
                break;

            case "RESTA":
                respuesta.setRespuesta(
                        payload.getNum1() - payload.getNum2()
                );
                break;
            case "MULTIPLICACION":
                respuesta.setRespuesta(
                        payload.getNum1() * payload.getNum2()
                );
                break;
        }

        respuesta.setOperacionRealizada(payload.getOperacion());

        return respuesta;
    }

}