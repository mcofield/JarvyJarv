package Controller;

import Request.Request;
import Request.SpotifyRequest;
import Request.Command;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.AddTrackToPlaylistRequest;
import com.wrapper.spotify.methods.PlaylistCreationRequest;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.AuthorizationCodeCredentials;
import com.wrapper.spotify.models.ClientCredentials;

import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;
import com.echonest.api.v4.Playlist;
import com.echonest.api.v4.PlaylistParams;
import com.echonest.api.v4.Song;
import com.echonest.api.v4.Track;
import com.wrapper.spotify.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.fail;

/**
 * Created by Joey on 5/6/2015.
 */
public class SpotifyController extends Controller {
    private final Api api;
    private  User currentUser = null;


    public SpotifyController() {
        api = Api.builder().clientId("352099a39ead45e3ab4d1353bd630db5").clientSecret("df1dd31ad04c48278afc05c35b77b53c").redirectURI("http://localhost:8888/callback").build();

        String code = "AQCc1RWw-UqDevdKNMoUpwmrOHEt9WOGkVHuJ55FczsQIWZqsyi0Dbl4DXOvef-uzkZJrjvWcQNMD3IIFIGyfRpVbimUxk3U30ENjHg9w5JPlj2KlOgbz8gSSIvDeOu-fyeQmjv9MwnzfRCue3CrbNB942zpsjfamKfQabFVsEr43J0JhdGwYw0sU_5S3QmQW3WOAxT7oFdcIZP3POBcfe14NVzroZaev0SHUxVbMA";

    }

    public void HandleRequest(Request request) throws Exception {

        if(request.getCommand() == Command.MAKE_PLAYLIST){
            this.makePlayList((SpotifyRequest)request);
        }
    }

    private void makePlayList(SpotifyRequest request) throws Exception {
            createSpotifyPlaylist(getEchoNestSongs(request),request);

    }

    private void createSpotifyPlaylist(Playlist playlist, SpotifyRequest request) throws Exception{
            PlaylistCreationRequest plCreateRequest = api.createPlaylist("mcofield",request.getArtist() + " Radio").publicAccess(false).build();
            com.wrapper.spotify.models.Playlist pl = plCreateRequest.get();
            List<String> songs = new ArrayList<String>();
            for (Song song : playlist.getSongs()) {
                Track track = song.getTrack("spotify-WW");
                songs.add("spotify:track:" + track.getForeignID());
            }
            AddTrackToPlaylistRequest addTrackReq = api.addTracksToPlaylist("mcofield",pl.getId(),songs).build();
            addTrackReq.get();


    }

    private Playlist getEchoNestSongs(SpotifyRequest r)throws EchoNestException{
        EchoNestAPI en = new EchoNestAPI("6HNENQTQZZXUFISX3");
        PlaylistParams params = new PlaylistParams();
        params.addIDSpace("spotify-WW");
        params.setType(PlaylistParams.PlaylistType.ARTIST_RADIO);
        params.addArtist(r.getArtist());
        params.setResults(25);
        params.setLimit(true);

        return en.createStaticPlaylist(params);
    }
}
