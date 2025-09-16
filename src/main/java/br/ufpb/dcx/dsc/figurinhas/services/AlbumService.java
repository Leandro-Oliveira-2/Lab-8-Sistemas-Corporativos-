package br.ufpb.dcx.dsc.figurinhas.services;

import br.ufpb.dcx.dsc.figurinhas.exception.ResourceNotFoundException;
import br.ufpb.dcx.dsc.figurinhas.models.Album;
import br.ufpb.dcx.dsc.figurinhas.models.User;
import br.ufpb.dcx.dsc.figurinhas.repository.AlbumRepository;
import br.ufpb.dcx.dsc.figurinhas.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlbumService {
    private AlbumRepository albumRepository;
    private UserRepository userRepository;

    public AlbumService(AlbumRepository albumRepository, UserRepository userRepository)
    {
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
    }

    public Album getAlbum(Long id){
        return albumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Album", id));
    }

    public List<Album> listAlbuns() {
        return albumRepository.findAll();
    }

    public Album saveAlbum(Album a, Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent()){
            a.setUser(userOpt.get());
            return albumRepository.save(a);
        }
        throw new ResourceNotFoundException("User", userId);
    }

    public void deleteAlbum(Long id) {
        if(!albumRepository.existsById(id)){
            throw new ResourceNotFoundException("Album", id);
        }
        albumRepository.deleteById(id);
    }

    public Album updateAlbum(Long id, Album f) {
        Optional<Album> figOpt = albumRepository.findById(id);
        if(figOpt.isPresent()){
            Album toUpdate = figOpt.get();
            toUpdate.setNome(f.getNome());
            return toUpdate;
        }
        throw new ResourceNotFoundException("Album", id);
    }
}
