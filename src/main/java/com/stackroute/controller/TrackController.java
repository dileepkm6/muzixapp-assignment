package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TrackController
{
    @Autowired
    private TrackService trackService;
//    @RequestMapping("/")
//    public String displayAllTrack(ModelMap modelMap)
//    {
//        modelMap.addAttribute("trackList",trackService.getAllTrack());
//        return "index";
//    }
//    @RequestMapping("/saveTrack")
//    public String saveTrack(@RequestParam("trackName") String trackName,@RequestParam("comment") String comment,ModelMap modelMap)
//    {
//        Track track=new Track();
//        track.setTrackName(trackName);
//        track.setComments(comment);
//        if(trackService.saveTrack(track))
//        {
//            modelMap.addAttribute("trackList",trackService.getAllTrack());
//        }
//        return "index";
//    }
//    @RequestMapping("/updateComment")
//    public String updateComment(@RequestParam("trackId") int trackId,@RequestParam("comment") String comment,ModelMap modelMap)
//    {
//           Track track=new Track();
//           track.setTrackId(trackId);
//           track.setComments(comment);
//           if(trackService.updateComment(track))
//           {
//               modelMap.addAttribute("trackList",trackService.getAllTrack());
//           }
//           return "index";
//    }
//    @RequestMapping("/update")
//    public String update(@RequestParam("trackId") int trackId, ModelMap modelMap)
//    {
//        modelMap.addAttribute("trackId",trackId);
//        return "update";
//    }
//    @RequestMapping("/delete")
//    public String delete(@RequestParam("trackId") int trackId,ModelMap modelMap)
//    {
//        trackService.deleteTrack(trackId);
//        modelMap.addAttribute("trackList",trackService.getAllTrack());
//        return "index";
//    }
    //adding track to database
    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try
        {
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Track successfully saved", HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    //deleting track from the database
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody int trackId)
    {
        ResponseEntity responseEntity;
        try
        {
            trackService.deleteTrack(trackId);
            responseEntity=new ResponseEntity<String>("Track successfully removed from the database", HttpStatus.OK);
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    //displaying all track
    @GetMapping("/getAllTrack")
    public ResponseEntity<?> getAllTrack() {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTrack(), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    //updating comment
    @PostMapping("/updateComment/{trackId}")
    public ResponseEntity<?> updateComment(@PathVariable int trackId,@RequestBody String comment) {
        ResponseEntity responseEntity;
        Boolean isUpdated=trackService.updateComment(trackId,comment);
        if(isUpdated)
        {
            responseEntity = new ResponseEntity<String>("comment updated", HttpStatus.OK);
        }
        else
        {
            responseEntity = new ResponseEntity<String>("given track not exist in database", HttpStatus.CONFLICT);
        }

        return responseEntity;
    }

}
