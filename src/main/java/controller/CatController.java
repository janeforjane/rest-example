package controller;

import model.Cat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CatService;
import utils.PropertiesClass;
import utils.PropertiesSecondClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @RestController— говорит спрингу, что данный класс является REST контроллером.
 * Т.е. в данном классе будет реализована логика обработки клиентских запросов
 * @RequestMapping(value = "/cat" нужно чтобы разделить несколько сервисов в одном приложении.
 * в зависимости от запроса (/cat) будет обращаться в соответствующий контроллер
 */
@RestController
//@RequestMapping(value = "/cat")
@RequestMapping
public class CatController {

    private static final Logger log = LogManager.getLogger(CatController.class);

    private final CatService catService;
    //todo почему-то идея потребовала сделать конструктор



    /** @Autowired — говорит спрингу, что в этом месте необходимо внедрить зависимость.
     * В конструктор мы передаем интерфейс CatService.
     * Реализацию данного сервиса мы пометили аннотацией @Service,
     * и теперь спринг сможет передать экземпляр этой реализации в конструктор контроллера.
     */
    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @Autowired
    public PropertiesClass propertiesClass;

    @Autowired
    public PropertiesSecondClass propertiesSecondClass;

    @Value("${application.animal}")
    public String animal;


    /**
     * @PostMapping(value = "/cats") — здесь мы обозначаем,
     * что данный метод обрабатывает POST запросы на адрес /cats
     * @param cat - значение этого параметра подставляется из тела запроса.
     *            Об этом говорит аннотация  @RequestBody
     * @return ResponseEntity — специальный класс для возврата ответов.
     * С помощью него мы сможем в дальнейшем вернуть клиенту HTTP статус код.
     */
    @PostMapping(value = "/cats")
    public ResponseEntity<?> create(@RequestBody Cat cat) {

        log.info("I'm in PostMapping method");
        System.out.println("I'm in PostMapping method");
        catService.create(cat);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * @GetMapping(value = "/cats") - данный метод обрабатывает GET запросы на адрес /cats
     * @return - возвращаем клиенту HTTP статус код + список котов
     */

    @GetMapping(value = "/cats")
//    @GetMapping
    public ResponseEntity<List<Cat>> read() {

        log.info("I'm in GetMapping List method");
        System.out.println("I'm in GetMapping List method");
        final List<Cat> catList = catService.readAll();

//        return catList != null &&  !catList.isEmpty()
//                ? new ResponseEntity<>(catList, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (catList != null &&  !catList.isEmpty()){
            return new ResponseEntity<>(catList, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @param id - Переменная, которая определена в URI. value = "/cats/{id}".
     *           Мы указали ее в фигурных скобках. А в параметрах метода принимаем
     *           её в качестве int переменной, с помощью аннотации @PathVariable(name = "id").
     */

    @GetMapping(value = "/cats/{id}")
    public ResponseEntity<Cat> read(@PathVariable(name = "id") int id) {
        log.info("I'm in GetMapping by id method");
        final Cat cat = catService.read(id);
        //todo зачем final?

        return cat != null
                ? new ResponseEntity<>(cat, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }



    @PutMapping(value = "/cats/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Cat cat) {
        log.info("I'm in PutMapping method");
        final boolean updated = catService.update(cat, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @DeleteMapping(value = "/cats/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        log.info("I'm in DeleteMapping method");
        final boolean deleted = catService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/mapsettings")
    public ResponseEntity<Cat> getMapSettings() {
        log.info("I'm in GetMapping getMapSettings method");

        Map<String, String> settings = propertiesClass.getSettings();

        System.out.println("Map settings:");

        for (Map.Entry<String, String> stringStringEntry : settings.entrySet()) {
            System.out.println(stringStringEntry);
        }


        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "/listsettings")
    public ResponseEntity<Cat> getListSettings() {
        log.info("I'm in GetMapping getListSettings method");

        List<String> list = new ArrayList<>();

        for (String s : propertiesSecondClass.getList()) {
            list.add(s);
        }

        System.out.println("List settings:" + list.size());

        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("Animal is: " + animal);


        return new ResponseEntity<>(HttpStatus.OK);

    }



}





















