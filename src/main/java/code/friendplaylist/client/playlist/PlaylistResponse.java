package code.friendplaylist.client.playlist;

import java.util.List;

public record PlaylistResponse(
    String href,
    int limit,
    String next,
    int offset,
    String previous,
    int total,
    List<PlaylistItem> items
) {}


