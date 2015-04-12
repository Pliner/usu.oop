using System;
using System.Collections.Generic;
using System.Linq;
using NUnit.Framework;
using Rhino.Mocks;

namespace RhinoMockExample
{
    [TestFixture]
    public class Tests
    {
        private MockRepository repository;
        private IWorkerRepository workerRepository;
        private IWageRepository wageRepository;
        private WorkerTotalPaidCalculator workerTotalPaidCalculator;

        [SetUp]
        public void SetUp()
        {
            repository = new MockRepository();
            workerRepository = NewMock<IWorkerRepository>();
            wageRepository = NewMock<IWageRepository>();
            workerTotalPaidCalculator = new WorkerTotalPaidCalculator(workerRepository, wageRepository);
        }

        [TearDown]
        public void TearDown()
        {
            repository.VerifyAll();
        }

        private T NewMock<T>()
        {
            var mock = repository.StrictMock<T>();
            mock.Replay();
            return mock;
        }

        [Test]
        public void ShouldCalculateIfNoWorkers()
        {
            workerRepository.Expect(x => x.GetWorkers()).Return(new Worker[0]);
            Assert.IsEmpty(workerTotalPaidCalculator.Calculate());
        }

        [Test]
        public void ShouldCalculate()
        {
            var worker = new Worker{Id = Guid.NewGuid()};
            workerRepository.Expect(x => x.GetWorkers()).Return(new[] {worker});
            wageRepository.Expect(x => x.GetWages(worker.Id)).Return(new[] {new Wage {Total = 10}, new Wage {Total = 20}});
            var totalPaidToWorker = workerTotalPaidCalculator.Calculate();
            Assert.IsNotEmpty(totalPaidToWorker);
            Assert.AreEqual(30, totalPaidToWorker[worker.Id]);
        }
    }

    public class WorkerTotalPaidCalculator
    {
        private readonly IWorkerRepository workerRepository;
        private readonly IWageRepository wageRepository;

        public WorkerTotalPaidCalculator(IWorkerRepository workerRepository, IWageRepository wageRepository)
        {
            this.workerRepository = workerRepository;
            this.wageRepository = wageRepository;
        }

        public Dictionary<Guid, decimal> Calculate()
        {
            return workerRepository.GetWorkers()
                .ToDictionary(x => x.Id, w => wageRepository.GetWages(w.Id).Sum(x => x.Total));
        }
    }

    public interface IWorkerRepository
    {
        Worker[] GetWorkers();
    }

    public class Worker
    {
        public Guid Id { get; set; }
    }

    public interface IWageRepository
    {
        Wage[] GetWages(Guid workerId);
    }

    public class Wage
    {
        public decimal Total { get; set; }
    }
}
