class CatePdList {
    constructor(totalCount, pageNo, rowsPerPage, dataList){
        this.totalCount = totalCount;   //총 상품 개수
        this.pageNo = pageNo;           //페이지 번호
        this.rowsPerPage = rowsPerPage; //한 페이지 당 상품 개수
        this.dataList = dataList;       //ProductListDataDto 타입의 Array
    }
}

export default CatePdList