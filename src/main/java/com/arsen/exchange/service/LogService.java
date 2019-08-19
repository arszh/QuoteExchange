package com.arsen.exchange.service;

import com.arsen.exchange.model.Log;
import org.springframework.web.bind.annotation.RequestMethod;

public interface LogService {

    Log createLog(RequestMethod method, Long idObj, int status, String message);

    Log save(Log log);
}
