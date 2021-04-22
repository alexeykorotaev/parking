package org.home.parking.barrier;

import org.home.parking.info.InfoTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarrierServiceImpl implements BarrierService {
    private final InfoTableService infoTableService;

    @Autowired
    public BarrierServiceImpl(InfoTableService infoTableService) {
        this.infoTableService = infoTableService;
    }

    @Override
    public EntranceBarrier getEntranceBarrier() {
        EntranceBarrier barrier = infoTableService.getInfoTable().getNumberOfFreePlaces() > 0 ?
                new EntranceBarrier(State.GO) : new EntranceBarrier(State.STOP);
        return barrier;
    }
}
