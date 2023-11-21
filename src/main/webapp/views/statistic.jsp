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
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
            <div class="btnHolder">
              <input id="monthpicker" type="month" value="2023-11">
              <button id="customerBtn">Khách hàng</button>
              <button id="salerBtn">Saler</button>
              <button id="productBtn">Sản phẩm</button>
              <button id="revenueBtn">Doanh thu</button>
            </div>
            <div class="chart-container current">
              <canvas id="mychart"></canvas>
              <table id="dataTable">
              </table>
            </div>

          </div>
          <script type="application/javascript" src="/js/statistic/salerChart.js"></script>
          <script type="application/javascript" src="/js/statistic/customerChart.js"></script>
          <script type="application/javascript" src="/js/statistic/productChart.js"></script>
          <script type="application/javascript" src="/js/statistic/revenueChart.js"></script>


          <script>
            getCustomerData(
              document.getElementById("monthpicker").value,
              document.getElementById('mychart')
            );
            document.getElementById("customerBtn").addEventListener("click", e => {
              clearChart();
              getCustomerData(
                document.getElementById("monthpicker").value,
                document.getElementById('mychart')
              );
            });
            document.getElementById("salerBtn").addEventListener("click", e => {
              clearChart();
              chart = getSalerData(
                document.getElementById("monthpicker").value,
                document.getElementById('mychart')
                );
            });
            document.getElementById("productBtn").addEventListener("click", e => {
              clearChart();
              chart = getProductData(
                document.getElementById("monthpicker").value,
                document.getElementById('mychart')
              );
            });
            document.getElementById("revenueBtn").addEventListener("click", e => {
              clearChart();
              chart = getRevenueData(
                document.getElementById("monthpicker").value,
                document.getElementById('mychart')
              );
            });
            
            function clearChart() {
              let cas = document.getElementById("mychart");
              let pr = cas.parentElement;
              cas.remove();
              pr.innerHTML = `<canvas id="mychart"></canvas>
                              <table id="dataTable">
                              </table>`;
            }
          </script>

        </div>
        <style>
          @keyframes slide {
            0%{
              transform: translateY(100%);
            }
          }
          .btnHolder > button {
            margin: 5px;
            width: 150px;
            height: 35px;
            border-radius: 5px;
            background-color: var(--color-primary);
            color: #fff;
            cursor: pointer;
          }

          .chart-container {
            height: 60%;
            display: none;
            flex-wrap: wrap;
            justify-content: center;
            align-items: center;
            animation: slide .5s;
            
          }

          .h-table {
            width: 60%;
            font-size: 13px;
          }

          * {
            box-sizing: border-box;
          }

          .current {
            display: flex;
          }
        </style>
      </body>

      </html>