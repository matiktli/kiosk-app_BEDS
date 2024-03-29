package com.kiosk.service.base;

import com.kiosk.dao.EventRepo;
import com.kiosk.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService extends BaseService<Event> {

    @Autowired
    public EventService(EventRepo repo) {
        super(repo);
    }
}
