package org.home.parking.barrier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarrierController {
    private final BarrierService barrierService;
    private final BarrierModelAssembler assembler;

    @Autowired
    public BarrierController(BarrierService barrierService, BarrierModelAssembler assembler) {
        this.barrierService = barrierService;
        this.assembler = assembler;
    }

    @GetMapping("/barrier")
    public EntityModel<EntranceBarrier> getEntranceBarrier() {
        EntranceBarrier barrier = barrierService.getEntranceBarrier();
        return assembler.toModel(barrier);
    }
}
