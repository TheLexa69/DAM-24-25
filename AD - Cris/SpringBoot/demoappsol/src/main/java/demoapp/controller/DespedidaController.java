package demoapp.controller;

import demoapp.service.DespedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


    @Controller
    public class DespedidaController {

        private final DespedidaService service;

        @Autowired
        public DespedidaController(DespedidaService service) {
            this.service = service;
        }

        @RequestMapping("/despedida/{nombre}")
        public @ResponseBody String despedir(@PathVariable(value="nombre") String nombre) {
            return service.despedir(nombre);
        }

    }
