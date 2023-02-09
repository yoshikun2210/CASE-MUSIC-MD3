package rikkei.academy.service.band;

import rikkei.academy.model.singer.Band;
import rikkei.academy.model.singer.Singer;
import rikkei.academy.service.IGenericService;

import java.util.List;

public interface IBandService  extends IGenericService<Band> {
    List<Band> findAll();
   Band findById(int id);
   void deleteById(int id);
    List<Band> findByName(String name);
}
