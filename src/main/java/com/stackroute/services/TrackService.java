package com.stackroute.services;

import com.stackroute.domain.Track;

import java.util.List;

public interface TrackService
{
    public boolean saveTrack(Track track);
    public List<Track> getAllTrack();
    public boolean updateComment(int trackId,String comment);
    public void deleteTrack(int trackId);
}
