package com.fabian.PlaylistManagerAPI.exceptions;

/**
 * Exception to notify the error in name playlist.
 */
public class BadPlaylistNotFoundException extends RuntimeException {
    public BadPlaylistNotFoundException(String message) {
        super(message);
    }
}
