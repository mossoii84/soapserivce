package com.soap.demo.ws;


import com.javaspringclub.gs_ws.GetMovieByIdRequest;
import com.javaspringclub.gs_ws.GetMovieByIdResponse;
import com.javaspringclub.gs_ws.MovieType;
import com.soap.demo.entity.MovieEntity;
import com.soap.demo.repo.MoviesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EndPoint {

    public static final String NAMESPACE = "http://www.soap.demo.ws.com/movies-ws";

    @Autowired
    MoviesRepository repository;


    @PayloadRoot(namespace = NAMESPACE, localPart = "getMovieByIdRequest")
    @ResponsePayload
    public GetMovieByIdResponse getMovieById (@RequestPayload GetMovieByIdRequest request) {
        GetMovieByIdResponse response = new GetMovieByIdResponse();
        MovieEntity movie = repository.findById(request.getMovieId()).get();
        MovieType movieType = new MovieType();
        BeanUtils.copyProperties(movie, movieType);
        response.setMovieType(movieType);
        return response;
    }

}
