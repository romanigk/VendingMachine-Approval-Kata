using System.Globalization;

namespace VendingMachine_Approval_Kata;

public class VendingMachine
{
    private readonly CultureInfo _en_Us_Culture;

    public VendingMachine()
    {
        _en_Us_Culture = CultureInfo.CreateSpecificCulture("en-US");
    }
    public List<int> Coins { get; } = new();
    public int Balance()
    {
        return Coins.Sum();
    }
  
    public string Display()
    {
        var balance = Balance();
        return balance != 0 ? FormatAsDollars(balance) : "INSERT COIN";
    }

    private string FormatAsDollars(int cents)
    {
        return (cents / 100.0).ToString("C", _en_Us_Culture);
    }

    public void InsertCoin(int coin)
    {
        Coins.Add(coin);
    }

}