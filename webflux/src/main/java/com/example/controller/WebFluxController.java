package com.example.controller;

import com.example.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author WXY
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class WebFluxController {

    private Map<Long, User> map = new HashMap<Long,User>(10);
    @PostConstruct
    public void init(){
        map.put(1L,new User(1L,"admin","admin"));
        map.put(2L,new User(2L,"admin2","admin2"));
        map.put(3L,new User(3L,"admin3","admin3"));
    }
    @GetMapping("/getAll")
    @CrossOrigin
    public Flux<User> getAllUser(){
        return Flux.fromIterable(map.entrySet().stream().map(Map.Entry::getValue)
                .collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public Mono<User> getUserById(@PathVariable("id") Long id){
        return Mono.just(map.get(id));
    }
    @PostMapping("/save")
    public Mono<ResponseEntity<String>> save(@RequestBody User user){
        map.put(user.getUid(),user);
        return Mono.just(new ResponseEntity<>("添加成功", HttpStatus.CREATED));
    }


    // 阻塞5秒钟
    private String createStr() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
        }
        return "some string";
    }

    // 普通的SpringMVC方法
    @GetMapping("/1")
    private String get1() {
        log.info("get1 start");
        String result = createStr();
        log.info("get1 end.");
        return result;
    }

    // WebFlux(返回的是Mono)
    @GetMapping("/2")
    private Mono<String> get2() {
        log.info("get2 start");
        Mono<String> result = Mono.fromSupplier(() -> createStr());
        log.info("get2 end.");
        return result;
    }

    /**
     * Flux : 返回0-n个元素
     * 注：需要指定MediaType
     * @return
     */
    @GetMapping(value = "/3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<String> flux() {
        Flux<String> result = Flux
                .fromStream(IntStream.range(1, 6).mapToObj(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                    }
                    return "flux data--" + i;
                }));
        return result;
    }
}