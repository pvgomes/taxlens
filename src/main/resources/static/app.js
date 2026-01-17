document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('countryForm');
  const input = document.getElementById('countryInput');
  const result = document.getElementById('result');

  form.addEventListener('submit', async (e) => {
    e.preventDefault();
    const country = input.value.trim();
    if (!country) {
      result.innerHTML = '<p class="text-sm text-gray-500">Enter a country code.</p>';
      return;
    }

    result.innerHTML = '<p class="text-sm text-gray-500">Loading…</p>';
    try {
      const resp = await fetch(`/api/cgt?country=${encodeURIComponent(country)}`);
      const data = await resp.json();
      if (data.error) {
        result.innerHTML = `<div class="text-red-600">${data.error}</div>`;
      } else {
        result.innerHTML = `
          <div class="p-4 border rounded bg-gray-50">
            <div class="text-lg font-medium">Rate: ${data.rate}%</div>
            <div class="text-sm text-gray-600 mt-1">${data.note || ''}</div>
          </div>
        `;
      }
    } catch (err) {
      result.innerHTML = '<div class="text-red-600">Request failed</div>';
    }
  });
});
