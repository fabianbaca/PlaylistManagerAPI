package com.fabian.PlaylistManagerAPI.exceptions;

/**
 * Exception to notify the error in name playlist.
 */
public class BadPlaylistNameException extends RuntimeException {
    public BadPlaylistNameException(String message) {
        super(message);
    }
}
