<#-- @ftlvariable name="order" type="de.admir.taze.model.order.Order" -->
<#-- @ftlvariable name="entry" type="de.admir.taze.model.order.OrderEntry" -->
<#-- @ftlvariable name="confirmationLink" type="java.lang.String" -->

<div>
    <h1>Jos samo jedan korak</h1>
    <p>Vasa narudzba je slijedeca</p>
    <table style="text-align: left">
        <tr>
            <th>Proizvod</th>
            <th style="text-align: center">Kolicina</th>
            <th style="text-align: right">Cijena</th>
        </tr>
        <#list order.entries as entry>
            <tr>
                <td>
                    <div>${entry.product.name}</div>
                    <img src="${entry.product.listImage}">
                </td>
                <td style="text-align: center">${entry.amount}</td>
                <td style="text-align: right">${entry.totalPrice} KM</td>
            </tr>
        </#list>
        <tr>
            <td colspan="2">Preuzimanje</td>
            <td style="text-align: right">${order.pickupType.text} (+${order.pickupType.price} KM)</td>
        </tr>
        <tr>
            <td colspan="2">Ukupna cijena</td>
            <td style="text-align: right">${order.totalPrice} KM</td>
        </tr>
    </table>
    <p>
        Naruceno: ${order.updated?date}, ${order.updated?time}
        <br>
        Adresa za dostavu (ukoliko ste odabrali dostavu): ${order.customer.address}
    </p>
    <p>
        Molimo Vas da klikom na slijedecu link potvrdite Vasu narudzbu
    </p>
    <a href="${confirmationLink}">Potvrdi narudzbu</a>
</div>
