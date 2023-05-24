package projekt.edziekanat.io.service;

import projekt.edziekanat.io.entites.Ocena;

import java.util.List;

public interface OcenaService {

    List<Ocena> findAllByStudentId(int theId);
}
