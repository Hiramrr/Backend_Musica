package miyu.smart.BackendMusica.service;

import miyu.smart.BackendMusica.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancionService{

    @Autowired
    private CancionRepository cancionRepository;
}
