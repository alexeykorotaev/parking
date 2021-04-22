package org.home.parking.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoTableController {

    private final InfoTableService infoTableService;
    private final InfoTableModelAssembler assembler;

    @Autowired
    public InfoTableController(InfoTableService infoTableService, InfoTableModelAssembler assembler) {
        this.infoTableService = infoTableService;
        this.assembler = assembler;
    }


    @GetMapping("/info")
    public EntityModel<InfoTable> getInfoTable() {
        InfoTable infoTable = infoTableService.getInfoTable();
        return assembler.toModel(infoTable);
    }
}
