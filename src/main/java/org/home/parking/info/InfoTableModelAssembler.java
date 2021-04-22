package org.home.parking.info;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InfoTableModelAssembler implements RepresentationModelAssembler<InfoTable, EntityModel<InfoTable>> {
    @Override
    public EntityModel<InfoTable> toModel(InfoTable infoTable) {
        return EntityModel.of(infoTable,
                linkTo(methodOn(InfoTableController.class).getInfoTable()).withRel("/info"));
    }
}
