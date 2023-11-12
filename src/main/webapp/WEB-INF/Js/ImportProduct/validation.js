const qty = document.getElementById("qty-product");
const errorQty = document.getElementById("error-qty-products");
const validationQty=()=>{
    const vndValue = qty.value.trim();
    if (!(/^\d+(\.\d{1,2})?$/.test(vndValue) && parseInt(vndValue) > 0)) {
        errorQty.textContent = 'Vui lòng nhập một số lớn hơn 0.';
    } else {
        errorQty.textContent = '';
    }
}
export {qty,errorQty,validationQty}