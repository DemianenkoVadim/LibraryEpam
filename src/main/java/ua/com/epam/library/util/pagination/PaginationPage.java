package ua.com.epam.library.util.pagination;

public class PaginationPage {

    private int page;
    private int totalPage;
    private int previousPageUi;
    private int nextPageUi;
    private int currentPageUi;
    private int activePage;
    private boolean isFirstPage;
    private boolean isLastPage;

    public PaginationPage() {
    }

    public int getActivePage() {
        return activePage;
    }

    public void setActivePage(int activePage) {
        this.activePage = activePage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPreviousPageUi() {
        return previousPageUi;
    }

    public void setPreviousPageUi(int previousPageUi) {
        this.previousPageUi = previousPageUi;
    }

    public int getNextPageUi() {
        return nextPageUi;
    }

    public void setNextPageUi(int nextPageUi) {
        this.nextPageUi = nextPageUi;
    }

    public int getCurrentPageUi() {
        return currentPageUi;
    }

    public void setCurrentPageUi(int currentPageUi) {
        this.currentPageUi = currentPageUi;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }
}
