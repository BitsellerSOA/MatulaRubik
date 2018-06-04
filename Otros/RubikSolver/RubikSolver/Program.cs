using System;
using System.Collections.Generic;

namespace RubikSolver
{
    class Program
    {

        static void Main(string[] args)
        {
            Cubo s = new Cubo();
            Console.WriteLine("F");
            s.GetFrontal().ForEach(x=>Console.WriteLine(x));
            Console.WriteLine("R");
            s.GetRight().ForEach(x => Console.WriteLine(x));
            Console.WriteLine("B");
            s.GetBack().ForEach(x => Console.WriteLine(x));
            Console.WriteLine("L");
            s.GetLeft().ForEach(x => Console.WriteLine(x));
            Console.WriteLine("U");
            s.GetUp().ForEach(x => Console.WriteLine(x));
            Console.WriteLine("D");
            s.GetDow().ForEach(x => Console.WriteLine(x));
            Console.ReadLine();
        }
    }
    
}
