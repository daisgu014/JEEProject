<%@ taglib prefix="c" uri="jakarta.tags.core" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

      <html>

      <head>
        <title>Dashboard</title>
        <link rel="stylesheet" href="/css/productStyle.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />


      </head>

      <body>
        <div class="container">
          <c:choose>
            <c:when test="${user.role eq 'SALE'}">
              <jsp:include page="../components/SidebarSale.jsp" />
            </c:when>
            <c:otherwise>
              <jsp:include page="../components/sidebar.jsp" />
            </c:otherwise>
          </c:choose>
          <div class="main--content">
            <div class="chart-container">
              <canvas id="myChart"></canvas>
              <table id="customerTable">
                <thead>
                  <tr>
                    <th>Mã Khách Hàng</th>
                    <th>Họ tên</th>
                    <th>Số đơn đã mua</th>
                    <th>Tổng tiền đã mua</th>
                  </tr>
                </thead>
                
              </table>
            </div>

            <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

            <script>
              const ctx = document.getElementById('myChart');
              function getData() {
                const res = fetch("/admin/statistic/customer")
                  .then((response) => response.json())
                  .then((data) => {
                    drawChart(data[0]);
                    document.getElementById("customerTable").innerHTML += drawTable(data[0]);
                  });
              }
              function drawTable(data) {
                let len = data.id.length;
                let s = "";
                for (let i = 0; i < len; i++) {
                  s += `<tr>
                      <td>` + data.id[i] + `</td>
                      <td>` + data.fullName[i] + `</td>
                      <td>` + data.orderCount[i] + `</td>
                      <td>` + data.total[i] + `</td>
                    </tr>`
                }
                return s;
              }

              function drawChart(data) {
                new Chart(ctx, {
                  type: 'bar',
                  data: {
                    labels: data.fullName,
                    datasets: [{
                      label: 'Số đơn đã mua',
                      data: data.orderCount,
                      borderWidth: 1,
                      yAxisID: 'orderCount'
                    }, {
                      label: 'Tổng tiền đã mua',
                      data: data.total,
                      borderWidth: 1,
                      yAxisID: 'total'
                    }]
                  },
                  options: {
                    responsive: true,
                    scales: {
                      orderCount: {
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
                        text: 'Top 5 Khách hàng mua nhiều nhất',
                      }
                    }
                  }
                });

              }

              getData();


            </script>
          </div>

        </div>
        <style>
          .chart-container {
            height: 60%;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            align-items: center;
          }
          #customerTable{
            width: 60%;
            font-size: 13px;
          }
          * {
            box-sizing: border-box;
          }
        </style>
      </body>

      </html>