package org.home.parking.barrier;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BarrierModelAssembler implements RepresentationModelAssembler<EntranceBarrier, EntityModel<EntranceBarrier>> {
    @Override
    public EntityModel<EntranceBarrier> toModel(EntranceBarrier barrier) {
        return EntityModel.of(barrier,
                linkTo(methodOn(BarrierController.class).getEntranceBarrier()).withRel("/barrier"));
    }
}
