<#-- @ftlvariable name="order" type="de.admir.taze.model.order.Order" -->
<#-- @ftlvariable name="entry" type="de.admir.taze.model.order.OrderEntry" -->
<#-- @ftlvariable name="confirmationLink" type="java.lang.String" -->

<div>
    <h1>Jos samo jedan korak</h1>
    <p>Vasa narudzba je slijedeca</p>
    <table>
        <tr>
            <th>Proizvod</th>
            <th>Kolicina</th>
            <th>Cijena</th>
        </tr>
        <#list order.entries as entry>
            <tr>
                <td>${entry.product.name}</td>
                <td>${entry.amount}</td>
                <td>${entry.totalPrice} KM</td>
            </tr>
        </#list>
        <tr>
            <td>Preuzimanje</td>
            <td colspan="2">${order.pickupType.text} (+${order.pickupType.price} KM)</td>
        </tr>
        <tr>
            <td>Ukupna cijena</td>
            <td colspan="2">${order.totalPrice} KM</td>
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
