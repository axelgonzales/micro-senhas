package pe.senhas.co.core.cocosenhas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import pe.senhas.co.core.cocosenhas.constant.Constant;
import pe.senhas.co.core.cocosenhas.domain.VideoEntity;
import pe.senhas.co.core.cocosenhas.exception.ExceptionResponse;
import pe.senhas.co.core.cocosenhas.model.VideoRequest;
import pe.senhas.co.core.cocosenhas.model.VideoResponse;
import pe.senhas.co.core.cocosenhas.model.VideoSearch;
import pe.senhas.co.core.cocosenhas.service.VideoService;

@RestController
@RequestMapping("v1/video")
@Api(value = "VideoController", produces = "application/json", tags = { "Controlador Video" })
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public List<VideoEntity> getAllVideos() {
        return videoService.findAllVideos();
    }

    @ApiOperation(value = "Obtiene Video por ID", tags = { "Controlador Video" })
    @GetMapping("search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Video encontrada", response = VideoEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<List<VideoRequest>> getVideoSearch(VideoSearch search) {
    	
        return new ResponseEntity<>(videoService.searchVideo(search),HttpStatus.OK);
         
    }
    
    @ApiOperation(value = "Obtiene Video por ID", tags = { "Controlador Video" })
    @GetMapping("level/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Video encontrada", response = VideoEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<VideoEntity> getVideoById(@PathVariable Long id) {
        return videoService.findVideoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Registra Video", tags = { "Controlador Video" })
    @PostMapping(consumes = {"multipart/form-data"})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Video registrada", response = VideoRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<VideoResponse> createVideo(
    		VideoRequest videoRequest,
    		MultipartFile multipart) {
    	
    	if (multipart.getSize()/(1024*1024) > 4) {
    		return new ResponseEntity<>(new VideoResponse(Constant.REG_VIDEO_LEGHT), HttpStatus.BAD_REQUEST);
		}
    	
        videoService.saveVideo(videoRequest,multipart);
        return new ResponseEntity<>(new VideoResponse(Constant.REG_INS_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza Video", tags = { "Controlador Video" })
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Video actualizada", response = VideoRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<VideoResponse> updateVideo(@PathVariable Long id, @RequestBody VideoRequest videoRequest) throws Exception {
        videoService.updateVideo(videoRequest, id);
        return new ResponseEntity<>(new VideoResponse(Constant.REG_ACT_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina Video", tags = { "Controlador Video" })
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Video eliminada", response = VideoRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<VideoResponse> deleteVideo(@PathVariable Long id) {
        videoService.deleteVideoById(id);
        return new ResponseEntity<>(new VideoResponse(Constant.REG_ELI_OK), HttpStatus.ACCEPTED);
    }
}
