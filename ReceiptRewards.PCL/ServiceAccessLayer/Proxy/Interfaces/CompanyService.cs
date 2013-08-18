using System;
using ReceiptRewards.PCL.Models;
namespace ReceiptRewards.PCL.ServiceAccessLayer.Proxy.Interfaces
{
    interface CompanyService
    {
        System.Collections.Generic.List<Company> getAll();
        Company getById(int companyId);
    }
}
