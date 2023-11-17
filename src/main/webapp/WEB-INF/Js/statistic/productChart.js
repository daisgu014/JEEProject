function getProductData(month, DOMelement) {
    fetch("/admin/statistic/customer",{
        method: "POST",
        headers:{
            'Content-Type': "application/json",
        },
        body:JSON.stringify(month.split("-"))
    })
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            document.getElementById("dataTable").innerHTML = drawProductTable(data[2]);
            return drawProductChart(DOMelement, data[2]);
        });
}
function drawProductTable(data) {
    let len = data.id.length;
    let s = `<table id="customerTable">
                <thead>
                    <tr>
                        <th>Mã Sản Phẩm</th>
                        <th>Tên sản phẩm</th>
                        <th>Số lượng đã bán</th>
                        <th>Tổng doanh thu</th>
                    </tr>
                </thead>
            </table>`;
    for (let i = 0; i < len; i++) {
        s += `  <tr>
                    <td>` + data.id[i] + `</td>
                    <td>` + data.productName[i] + `</td>
                    <td>` + data.productCount[i] + `</td>
                    <td>` + data.total[i].toLocaleString('it-IT', {style : 'currency', currency : 'VND'}) + `</td>
                </tr>`
    }
    return s;
}

function drawProductChart(DOMelement, data) {
    new Chart(DOMelement, {
        type: 'bar',
        data: {
            labels: data.productName,
            datasets: [{
                label: 'Số lượng đã bán',
                data: data.productCount,
                borderWidth: 1,
                yAxisID: 'productCount'
            }, {
                label: 'Tổng doanh thu',
                data: data.total,
                borderWidth: 1,
                yAxisID: 'total'
            }]
        },
        options: {
            responsive: true,
            scales: {
                productCount: {
                    type: 'linear',
                    display: true,
                    position: 'left',
                },
                total: {
                    type: 'linear',
                    display: true,
                    position: 'right',
                }
            },
            plugins: {
                title: {
                    display: true,
                    text: 'Top 10 Sản phẩm bán chạy nhất trong tháng',
                }
            }
        }
    });

}