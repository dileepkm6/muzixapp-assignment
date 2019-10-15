package com.stackroute.services;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrackServiceImpl implements TrackService
{
    private TrackRepository trackRepository;
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public boolean saveTrack(Track track)
    {
        Track savedTrack=trackRepository.save(track);
        if(savedTrack!=null)
        {
            return true;
        }
        return false;
    }

    @Override
    public List<Track> getAllTrack()
    {
        return trackRepository.findAll();
    }

    @Override
    public boolean updateComment(int trackId,String comment) {
        Track trackToChangeCom=trackRepository.getOne(trackId);
        if(trackToChangeCom!=null)
        {
            trackToChangeCom.setComments(comment);
            trackRepository.save(trackToChangeCom);
            return true;
        }
        return false;
    }

    @Override
    public void deleteTrack(int trackId)
    {
        trackRepository.deleteById(trackId);
    }
}
