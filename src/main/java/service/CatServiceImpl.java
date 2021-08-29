package service;

import model.Cat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CatServiceImpl implements CatService {

    private static final Logger log = LogManager.getLogger(CatServiceImpl.class);


    // Хранилище котов
    private static final Map<Integer, Cat> CAT_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID
    private static final AtomicInteger CAT_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Cat cat) {

        log.info("I'm in create method");

        final int catId = CAT_ID_HOLDER.incrementAndGet();

        cat.setId(catId);
        CAT_REPOSITORY_MAP.put(catId,cat);

    }

    @Override
    public List<Cat> readAll() {

        log.info("I'm in readAll method");

        List<Cat> catList = new ArrayList<>(CAT_REPOSITORY_MAP.values());
        return catList;
    }

    @Override
    public Cat read(int id) {
        log.info("I'm in read method");
        return CAT_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Cat cat, int id) {
        log.info("I'm in update method");
        if (CAT_REPOSITORY_MAP.containsKey(id)) {
            cat.setId(id);
            CAT_REPOSITORY_MAP.put(id, cat);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        log.info("I'm in delete method");
        return CAT_REPOSITORY_MAP.remove(id) != null;
    }
}
