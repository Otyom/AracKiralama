package com.example.AracKiralama.service;

import com.example.AracKiralama.dto.request.ImageSaveRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.dto.response.GetAllImageByCarIdResponseDto;
import com.example.AracKiralama.entity.Admin;
import com.example.AracKiralama.entity.Image;
import com.example.AracKiralama.entity.rentacar.Car;
import com.example.AracKiralama.exception.persons.AdminNotFoundException;
import com.example.AracKiralama.exception.persons.ExsistByEmail;
import com.example.AracKiralama.exception.persons.InvaildToken;
import com.example.AracKiralama.repository.IImageRepository;
import com.example.AracKiralama.utility.JwtTokenManeger;
import com.example.AracKiralama.utility.ServiceManeger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService extends ServiceManeger<Image,Long> {
    private final IImageRepository repository;
    private final JwtTokenManeger jwtTokenManeger;
    private final AdminService adminService;
    private final CarService carService;

    public ImageService(IImageRepository imageRepository, JwtTokenManeger jwtTokenManeger, AdminService adminService, CarService carService) {
        super(imageRepository);
        this.repository = imageRepository;
        this.jwtTokenManeger = jwtTokenManeger;
        this.adminService = adminService;
        this.carService = carService;
    }


    public BaseResponseDto addImage(ImageSaveRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Admin> admin= adminService.findById(id.get());
        if (admin.isEmpty()) {
            throw new AdminNotFoundException();
        }

        Optional<Car> car=carService.findById(dto.getCarId());
        if (car.isEmpty())throw new RuntimeException();
        Image image= Image.builder()
                .dosyaAdi(dto.getImageName())
                .cloudinaryUrl(dto.getCloudinaryUr())
                .carId(dto.getCarId())
                .build();
        save(image);
        return BaseResponseDto.builder()
                .httpStatus(HttpStatus.OK)
                .statusCode(200)
                .message("Resim kayıt edildi")
                .build();
    }

    public List<GetAllImageByCarIdResponseDto> getAllImageBycarId(String token, Long carId){
        Optional<Long> id = jwtTokenManeger.getIdByToken(token);
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Admin> admin= adminService.findById(id.get());
        if (admin.isEmpty()) {
            throw new AdminNotFoundException();
        }

        List<Image>images=repository.findByCarId(carId);
        List<GetAllImageByCarIdResponseDto> responseDtos=new ArrayList<>();

        for (Image image: images){
            responseDtos.add(GetAllImageByCarIdResponseDto.builder()
                    .cloudinaryUrl(image.getCloudinaryUrl())
                    .dosyaAdi(image.getDosyaAdi())
                    .carId(image.getCarId())
                    .build());
        }
        return responseDtos;
    }


}
