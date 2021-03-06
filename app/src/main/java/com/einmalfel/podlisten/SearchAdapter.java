package com.einmalfel.podlisten;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

class SearchAdapter extends BaseCursorRecyclerAdapter {
  public interface SearchClickListener {
    void onPodcastButtonTap(String rss_url);
  }

  private final SearchClickListener listener;

  public SearchAdapter(SearchClickListener listener) {
    super(null);
    this.listener = listener;
  }

  @Override
  public void onBindViewHolderCursor(RecyclerView.ViewHolder holder, Cursor cursor) {
    long id = cursor.getLong(cursor.getColumnIndexOrThrow("_ID"));
    SearchElementHolder sHolder = (SearchElementHolder) holder;
    sHolder.bind(cursor.getString(cursor.getColumnIndexOrThrow("title")),
                 cursor.getString(cursor.getColumnIndexOrThrow("description")),
                 cursor.getString(cursor.getColumnIndexOrThrow("rss_url")),
                 cursor.getString(cursor.getColumnIndexOrThrow("web_url")),
                 cursor.getLong(cursor.getColumnIndexOrThrow("period")),
                 id,
                 expandedElements.contains(id));
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(
        R.layout.search_list_element, parent, false);
    return new SearchElementHolder(v, listener, this);
  }
}
